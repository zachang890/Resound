//
//  NewsTableViewCell.swift
//  Resound
//
//  Created by Zachary Chang on 9/10/20.
//  Copyright © 2020 Zachary Chang. All rights reserved.
//

import UIKit

class NewsTableViewCell: UITableViewCell {

    @IBOutlet var headline: UILabel!
    @IBOutlet var source: UILabel!
    @IBOutlet var time: UILabel!
    @IBOutlet var thumb: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
