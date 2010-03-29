//
//  IntentionalIDEAppDelegate.m
//  IntentionalIDE
//
//  Created by cfregin on 29.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import "IntentionalIDEAppDelegate.h"

@implementation IntentionalIDEAppDelegate

@synthesize window;

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification {
	IntentionalClass *mainClass = [[IntentionalClass alloc] init];
	IntentionalMethod *mainMethod = [[IntentionalMethod alloc] initWithLanguage:JAVA_SCRIPT name:@"main" code:@"trace('hello');"];
	[mainClass addMethod:mainMethod];
	CodeExecutor *executor = [[CodeExecutor alloc] initWithRoot:mainClass];
	[executor start];
	
//	[mainMethod autorelease];
	[mainClass release];
	[executor release];
}

@end
