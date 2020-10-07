//
//  HeadlinesTableViewController.swift
//  Resound
//
//  Created by Zachary Chang on 9/6/20.
//  Copyright © 2020 Zachary Chang. All rights reserved.
//

import UIKit
import SafariServices

class HeadlinesTableViewController: UITableViewController, SFSafariViewControllerDelegate {
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .darkContent
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.cellLayoutMarginsFollowReadableWidth = true
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationController?.navigationBar.setBackgroundImage(UIImage(), for: .default)
        navigationController?.hidesBarsOnSwipe = true
        self.tableView.sectionHeaderHeight = 40
        self.tableView.backgroundColor = UIColor.cyan
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.hidesBarsOnSwipe = true
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 9
    }
    

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "datacell"
        let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as! NewsTableViewCell //downcasting, to return RestaurantTableViewCell instead of the default cell
        
        // Configure the cell...
        cell.headline.text = "bruh"
        cell.source.text = "nice"
        cell.time.text = "wyd"
        cell.contentView.backgroundColor = UIColor.cyan
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let urlString = "https://www.hackingwithswift.com"

        if let url = URL(string: urlString) {
            let vc = SFSafariViewController(url: url, entersReaderIfAvailable: true)
            vc.delegate = self

            present(vc, animated: true)
        }
    }
    
    override func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        //destructive makes the button red
        
        let shareAction = UIContextualAction(style: .normal, title: "Share") { (action, sourceView, completionHandler) in
            let defaultText = "Check out this article! Let's keep this in our thoughts today."

            let activityController: UIActivityViewController
            
            activityController = UIActivityViewController(activityItems: [defaultText], applicationActivities: nil)
            
            //accomodate once again for the IPAD
            if let popoverController = activityController.popoverPresentationController {
                if let cell = tableView.cellForRow(at: indexPath) {
                    popoverController.sourceView = cell
                    popoverController.sourceRect = cell.bounds
                }
            }
            self.present(activityController, animated: true, completion: nil)
            completionHandler(true)
        }
        shareAction.backgroundColor = UIColor(red: 254.0/255.0, green: 149.0/255.0, blue: 38.0/255.0, alpha: 1.0)
        shareAction.image = UIImage(systemName: "square.and.arrow.up")
        
        let swipeConfiguration = UISwipeActionsConfiguration(actions: [shareAction])
        return swipeConfiguration
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
