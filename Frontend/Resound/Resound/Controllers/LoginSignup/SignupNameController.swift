//
//  SignupNameController.swift
//  Resound
//
//  Created by Zachary Chang on 9/1/20.
//  Copyright © 2020 Zachary Chang. All rights reserved.
//

import UIKit

class SignupNameController: UIViewController {
    
    @IBOutlet var firstName: UITextField!
    @IBOutlet var lastName: UITextField!

    var activeTextField : UITextField? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        firstName.delegate = self
        lastName.delegate = self
        
        navigationController?.navigationBar.setBackgroundImage(UIImage(), for: .default)
        navigationController?.navigationBar.shadowImage = UIImage()
        
        
        NotificationCenter.default.addObserver(self, selector: #selector(SignupNameController.keyboardWillShow), name: UIResponder.keyboardWillShowNotification, object: nil)
      
        NotificationCenter.default.addObserver(self, selector: #selector(SignupNameController.keyboardWillHide), name: UIResponder.keyboardWillHideNotification, object: nil)
        

        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.setNavigationBarHidden(false, animated: animated)
    }


    @objc func keyboardWillShow(notification: NSNotification) {
        guard let keyboardSize = (notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue else {
            return
        }

        var shouldMoveViewUp = false

        var bottomOfTextField: Float?
        var topOfKeyboard: Float?
          // if active text field is not nil
        if let activeTextField = activeTextField {
            bottomOfTextField = Float(activeTextField.convert(activeTextField.bounds, to: self.view).maxY);
            
            topOfKeyboard = Float(self.view.frame.height - keyboardSize.height)

            // if the bottom of Textfield is below the top of keyboard, move up
            if bottomOfTextField! > topOfKeyboard! {
              shouldMoveViewUp = true
            }
        }

        if(shouldMoveViewUp) {
            self.view.frame.origin.y = CGFloat(0 - (bottomOfTextField! - topOfKeyboard!) - 100)
        }
    }

    @objc func keyboardWillHide(notification: NSNotification) {
      // move back the root view origin to zero
        self.view.frame.origin.y = 0
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

extension SignupNameController : UITextFieldDelegate {
  // when user select a textfield, this method will be called
  func textFieldDidBeginEditing(_ textField: UITextField) {
    // set the activeTextField to the selected textfield
    self.activeTextField = textField
  }
    
  // when user click 'done' or dismiss the keyboard
  func textFieldDidEndEditing(_ textField: UITextField) {
    self.activeTextField = nil
  }
}
