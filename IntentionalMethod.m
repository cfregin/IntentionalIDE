//
//  IntentionalMethod.m
//  IntentionalIDE
//
//  Created by cfregin on 29.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import "IntentionalMethod.h"


@implementation IntentionalMethod
@synthesize code,language,methodName;
-(id)initWithLanguage:(NSUInteger)lang name:(NSString*)name code:(NSString*)theCode{
	self = [super init];
	if(self){
		language = lang;
		compiled = NO;
		[self setCode:theCode];
		[self setMethodName:name];
	}
	return self;
}
-(BOOL)compile{
	return YES;
}
-(id)execute{
	if(compiled == NO){
		[self compile];
	}
	return nil;
}
@end
