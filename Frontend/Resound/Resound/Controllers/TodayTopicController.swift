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
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        .lightContent
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController?.isNavigationBarHidden = true
        updateText()
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
