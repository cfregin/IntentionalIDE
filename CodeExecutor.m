//
//  CodeExecutor.m
//  IntentionalIDE
//
//  Created by cfregin on 29.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import "CodeExecutor.h"


@implementation CodeExecutor
-(id)initWithRoot:(IntentionalClass*)rootClass{
	self = [super init];
	if(self){
		root = rootClass;
	}
	return self;
}
-(BOOL)start{
	[root callMethod:@"main"];
	return YES;
}
@end
