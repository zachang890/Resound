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
    
    //MARK: Sample data, used as a placeholder for when AWS is not running
    //Assume categories are: water crisis, world hunger, and climate change
    let headlines = ["Flint residents sue investment banks, accuse them of helping create water crisis", "Best time to address water crisis was ten years ago, the next best time is now", "Water crisis in Iran could bring ecological disaster", "Opinion/Letter: COVID worsens world hunger", "Food Crisis Grows Amid Coronavirus Pandemic", "Funding announced to study microbes to combat world hunger", "Climate change could mean fewer sunny days for hot regions banking on solar power", "Trillions of extra economic damages predicted in new study of climate change effects", "What Have We Learned in Thirty Years of Covering Climate Change?"]
    let sources = ["The Detroit News", "The Financial Express", "UPI News", "The Daily Progress", "The Wall Street Journal", "New Food", "Science Daily", "Phys.org", "The New Yorker"]
    let times = ["4 hours ago", "Yesterday", "2 days ago", "2 hours ago", "3 days ago", "Yesterday", "16 hours ago", "7 hours ago", "9 hours ago"]
    let urls=["https://www.detroitnews.com/story/news/michigan/flint-water-crisis/2020/10/07/flint-residents-blame-water-crisis-investment-banks-lawsuit/5912871002/", "https://www.financialexpress.com/opinion/best-time-to-address-water-crisis-was-ten-years-ago-the-next-best-time-is-now/2099594/", "https://www.upi.com/Top_News/Voices/2020/10/05/Water-crisis-in-Iran-could-bring-ecological-disaster/8821601900601/", "https://dailyprogress.com/opinion/letters/opinion-letter-covid-worsens-world-hunger/article_1c3fb8e7-4a34-508f-9b1d-2bd36dd61d8e.html", "https://www.wsj.com/articles/food-crisis-grows-amid-coronavirus-pandemic-11601991326", "https://www.newfoodmagazine.com/news/120897/funding-announced-to-study-microbes-to-combat-world-hunger/", "https://www.sciencedaily.com/releases/2020/10/201007123029.htm", "https://phys.org/news/2020-10-trillions-extra-economic-climate-effects.html", "https://www.newyorker.com/news/annals-of-a-warming-planet/what-have-we-learned-in-thirty-years-of-covering-climate-change"]
    let images = ["tap.png", "bread.png", "climate.png"]
    
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
        self.tableView.backgroundColor = UIColor.systemTeal
        
        navigationController?.navigationBar.largeTitleTextAttributes = [ NSAttributedString.Key.foregroundColor: UIColor(red: 0/255.0, green: 0/255.0, blue: 0/255.0, alpha: 1.0)]
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
        cell.headline.text = headlines[indexPath.row]
        cell.source.text = sources[indexPath.row]
        cell.time.text = times[indexPath.row]
        
        cell.thumb.image = UIImage(named: images[indexPath.row/3])
        
        cell.contentView.backgroundColor = UIColor.systemTeal
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let urlString = urls[indexPath.row]

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
