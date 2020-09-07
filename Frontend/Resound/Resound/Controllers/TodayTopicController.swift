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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        updateIP()
        
        // Do any additional setup after loading the view.
    }
    
    
    func updateIP() {
            
        let postEndpoint: String = "http://backend-main.eba-24mqhk9g.us-west-2.elasticbeanstalk.com/suggestions/topics-list"
                let session = URLSession.shared
                let url = URL(string: postEndpoint)!
                
                 // Make the POST call and handle it in a completion handler
                session.dataTask(with: url, completionHandler: { ( data: Data?, response: URLResponse?, error: Error?) -> Void in
                    // Make sure we get an OK response
                    guard let realResponse = response as? HTTPURLResponse,
                              realResponse.statusCode == 200 else {
                        print("Not a 200 response")
                                return
                    }
                    
                    // Read the JSON
                    do {
                        if let ipString = NSString(data:data!, encoding: String.Encoding.utf8.rawValue) {
                            // Print what we got from the call
                            print(ipString)
                        
                            // Parse the JSON to get the IP
                            let jsonDictionary = try JSONSerialization.jsonObject(with: data!, options: JSONSerialization.ReadingOptions.mutableContainers) as! NSDictionary
                            let origin = jsonDictionary["origin"] as! String
                           
                            // Update the label
                            self.performSelector(onMainThread: #selector(TodayTopicController.updateIPLabel(_:)), with: origin, waitUntilDone: false)
                        }
                    } catch {
                        print("bad things happened")
                    }
                } ).resume()
    }
    
    @objc func updateIPLabel(_ text: String) {
            self.firstTopic.text = "Your IP is " + text
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
