//
//  ViewController.swift
//  Resound
//
//  Created by Zachary Chang on 8/30/20.
//  Copyright © 2020 Zachary Chang. All rights reserved.
//

import UIKit

class LoginSignupController: UIViewController {
    
    @IBOutlet var loginSignupBackground: UIImageView!
    @IBOutlet var loginButton: UIButton!
    @IBOutlet var signupButton: UIButton!
    
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.setNavigationBarHidden(true, animated: animated)
    }


    override func viewDidLoad() {
        super.viewDidLoad()
        loginSignupBackground.image = UIImage(named: "Melancholy Cloud - Pawel Nolbert")
        loginButton.layer.cornerRadius = 15
        signupButton.layer.cornerRadius = 15
    }
    
//    override func prepare(for segue: UIStoryboardSegue, sender: Any?) { if segue.identifier == "showRestaurantDetail" {
//    if let indexPath = tableView.indexPathForSelectedRow {
//    let destinationController = segue.destination as! RestaurantDe
//    tailViewController
//    destinationController.restaurantImageName = restaurantImages[i
//    ndexPath.row] }
//    } }
}

