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
#import "JavaScriptLexer.h"
#import "JavaScriptParser.h"
@interface IntentionalIDEAppDelegate : NSObject <NSApplicationDelegate> {
    NSWindow *window;
	NSBundle *localFramework;
	NSArray *loadedClasses;
}

@property (assign) IBOutlet NSWindow *window;
@property (retain) NSArray *loadedClasses;
-(void)gotClasses:(NSNotification*)notification;
@end
