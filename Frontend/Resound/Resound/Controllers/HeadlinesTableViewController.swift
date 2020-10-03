//
//  HeadlinesTableViewController.swift
//  Resound
//
//  Created by Zachary Chang on 9/6/20.
//  Copyright © 2020 Zachary Chang. All rights reserved.
//

import UIKit

class HeadlinesTableViewController: UITableViewController {
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.cellLayoutMarginsFollowReadableWidth = true
        navigationController?.hidesBarsOnSwipe = true
        self.tableView.sectionHeaderHeight = 40
        self.tableView.backgroundColor = UIColor.black
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.hidesBarsOnSwipe = true
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 3
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 3
    }
    
    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        var ending = ["Topic 1", "Topic 2", "Topic 3"]
        return ending[section]
    }
    

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "datacell"
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as! NewsTableViewCell //downcasting, to return RestaurantTableViewCell instead of the default cell
        
        // Configure the cell...
        cell.headline.text = "bruh"
        cell.source.text = "nice"
        cell.time.text = "wyd"
        cell.contentView.backgroundColor = UIColor.black
        return cell
    }

    
    
    func accessDynamo(topic: String) { //current shut-off of aws systems
        let postEndpoint: String = "http://backend-main.eba-24mqhk9g.us-west-2.elasticbeanstalk.com/suggestions/today-topics-list" //ensure that today-topics-list are refreshed daily
        let session = URLSession.shared
        let url = URL(string: postEndpoint)!
                
        session.dataTask(with: url, completionHandler: { ( data: Data?, response: URLResponse?, error: Error?) -> Void in
            guard let realResponse = response as? HTTPURLResponse, realResponse.statusCode == 200 else {
                print("Not a 200 response")
                return
            }
                
            do {
                if let ipString = NSString(data:data!, encoding: String.Encoding.utf8.rawValue) {
                    print(ipString)
                
                    let jsonDictionary = try JSONSerialization.jsonObject(with: data!, options: JSONSerialization.ReadingOptions.mutableContainers) as! NSDictionary
                    let origin = jsonDictionary["list"] as! [String]
                   
                    self.performSelector(onMainThread: #selector(TodayTopicController.updateFirstLabel(_:)), with: origin[0], waitUntilDone: false)
                    self.performSelector(onMainThread: #selector(TodayTopicController.updateSecondLabel(_:)), with: origin[1], waitUntilDone: false)
                    self.performSelector(onMainThread: #selector(TodayTopicController.updateThirdLabel(_:)), with: origin[2], waitUntilDone: false)
                }
            } catch {
                print("bad things happened")
            }
        } ).resume()
    }
}
