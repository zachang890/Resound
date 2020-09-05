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
        
        // Do any additional setup after loading the view.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        retrieveTopics()
    }
    
    
    func retrieveTopics() {
        guard let dynamoAccess = URL(string: "http://backend-main.eba-24mqhk9g.us-west-2.elasticbeanstalk.com/suggestions/topics-list") else {
            print("Invalid URL")
            return
        }
        let request = URLRequest(url: dynamoAccess)
        
        URLSession.shared.dataTask(with: request) { data, response, error in
            if let data = data {
                if let decodedResponse = try? JSONDecoder().decode(TopicSuggestions.self, from: data) {
                    // we have good data – go back to the main thread
                    DispatchQueue.main.sync {
                        // update our UI
                        self.firstTopic.text = decodedResponse.titles[0]
                        self.secondTopic.text = decodedResponse.titles[1]
                        self.secondTopic.text = decodedResponse.titles[2]
                    }

                    // everything is good, so we can exit
                    return
                }
            }

            // if we're still here it means there was a problem
            print("Fetch failed: \(error?.localizedDescription ?? "Unknown error")")
        }.resume()
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
