import Foundation

@objc public class SMSInboxReader: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
