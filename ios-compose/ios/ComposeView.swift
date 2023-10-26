//
//  ComposeView.swift
//  TodoAppCompose
//
//  Created by Илья Гуля on 26.10.2023.
//

import SwiftUI
import Todo

struct ComposeView: UIViewControllerRepresentable {
    private var component: TodoRoot
    
    init(_ component: TodoRoot) {
        self.component = component
    }
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController(root: component)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
