//
//  IntentionalIDEAppDelegate.h
//  IntentionalIDE
//
//  Created by cfregin on 29.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import <Cocoa/Cocoa.h>
#import "CodeExecutor.h"
#import "IntentionalClass.h"
@interface IntentionalIDEAppDelegate : NSObject <NSApplicationDelegate> {
    NSWindow *window;
	
}

@property (assign) IBOutlet NSWindow *window;

@end
