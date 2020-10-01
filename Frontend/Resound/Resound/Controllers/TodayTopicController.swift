//
//  TodayTopicController.swift
//  Resound
//
//  Created by Zachary Chang on 9/5/20.
//  Copyright © 2020 Zachary Chang. All rights reserved.
//

import UIKit

class TodayTopicController: UIViewController {

    @IBOutlet var firstTopic: UILabel!
    @IBOutlet var secondTopic: UILabel!
    @IBOutlet var thirdTopic: UILabel!
    @IBOutlet var greeting: UILabel!
    @IBOutlet var date: UILabel!
    @IBOutlet var resound: UILabel!
    
    let greetings = ["Your focus for today:", "Let's think about these:", "Another day, here you go:", "Spread the word about these:"]
    
    let days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        updateText()
        
        self.navigationController?.isNavigationBarHidden = true
        let number = Int.random(in: 0..<4)
        greeting.text = greetings[number]
        
        let d = Date()
        let calendar = Calendar.current
        let yr = String(calendar.component(.year, from: d))
        let month = String(calendar.component(.month, from: d))
        let day = String(calendar.component(.day, from: d))
        let weekdayNum = calendar.component(.weekday, from: d)
        
        let weekdayName = days[weekdayNum - 1]
        
        date.text = weekdayName + ", " + month + "." + day + "." + yr
        date?.layer.masksToBounds = true
        date?.layer.cornerRadius = 5.0
        
        resound?.layer.masksToBounds = true
        resound?.layer.cornerRadius = 5.0
    }
    
    
    func updateText() {
        let postEndpoint: String = "http://backend-main.eba-24mqhk9g.us-west-2.elasticbeanstalk.com/suggestions/today-topics-list"
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
    
    @objc func updateFirstLabel(_ text: String) {
        self.firstTopic.text = text
    }
    
    @objc func updateSecondLabel(_ text: String) {
        self.secondTopic.text = text
    }
    
    @objc func updateThirdLabel(_ text: String) {
        self.thirdTopic.text = text
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
