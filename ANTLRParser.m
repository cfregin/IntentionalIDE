//
//  ANTLRParser.m
//  IntentionalIDE
//
//  Created by cfregin on 30.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import "ANTLRParser.h"


@implementation ANTLRParser
@synthesize antlrTask;
-(id)init{
	antlrTask = [[NSTask alloc] init];
	[antlrTask setLaunchPath:@"java"];
	
	//[antlrTask setCurrentDirectoryPath:<#(NSString *)path#>]
	NSLog(@"Corrent directory:%@",[antlrTask currentDirectoryPath]);
}

- (void) dealloc
{
	[antlrTask release];
	[super dealloc];
}

@end
