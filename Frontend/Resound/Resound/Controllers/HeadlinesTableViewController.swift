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
        return .darkContent
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.cellLayoutMarginsFollowReadableWidth = true
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
        cell.locationLabel.text = restaurants[indexPath.row].location
        cell.typeLabel.text = restaurants[indexPath.row].type
        return cell
    }

}
