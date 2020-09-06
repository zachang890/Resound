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
            
            // Setup the session to make REST GET call.  Notice the URL is https NOT http!!
        var request = URLRequest(url: URL(string: "backend-main.eba-24mqhk9g.us-west-2.elasticbeanstalk.com/suggestions/topics-list")!)
        request.httpMethod = "GET"

        let session = URLSession.shared
        let task = session.dataTask(with: request, completionHandler: { data, response, error -> Void in
            print(response!)
            do {
                let json = try JSONSerialization.jsonObject(with: data!) as! NSArray
                print(json)
            } catch {
                print("error")
            }
        })

        task.resume()
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
