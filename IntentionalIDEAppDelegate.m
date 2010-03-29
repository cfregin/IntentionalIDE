//
//  IntentionalIDEAppDelegate.m
//  IntentionalIDE
//
//  Created by cfregin on 29.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import "IntentionalIDEAppDelegate.h"
//#import "generated/clang.c"
@implementation IntentionalIDEAppDelegate

@synthesize window;
@synthesize loadedClasses;
-(void)gotClasses:(NSNotification*)notification{
	[self setLoadedClasses:[[notification userInfo] valueForKey:@"NSLoadedClasses"]];
}
- (void)applicationDidFinishLaunching:(NSNotification *)aNotification {
	[[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(gotClasses:) name:NSBundleDidLoadNotification object:nil];
	
//Load framework dynamicaly
	Class theClass2 = NSClassFromString(@"DynaLoaderTest");// [localFramework classNamed:@"DynaLoaderTest"];
	//Class schould be null
	assert(theClass2==nil);
	[theClass2 release];
	//now load
	localFramework = [NSBundle bundleWithPath:@"IntentionalObjectiveC.framework"];
	if([localFramework load]){
		Class theClass2 = NSClassFromString(@"DynaLoaderTest");// [localFramework classNamed:@"DynaLoaderTest"];
		assert(theClass2);
		//Class schould be null
		id myobj = [[theClass2 alloc] init];
		NSLog(@"Now:%@" , [myobj getTheName]);
		[myobj release];
		[theClass2 release];

	}
	//unload
	if([localFramework unload]){
		Class theClass3 = NSClassFromString(@"DynaLoaderTest");// [localFramework classNamed:@"DynaLoaderTest"];
	
		//assert(theClass3==nil);
	}
	

	
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
