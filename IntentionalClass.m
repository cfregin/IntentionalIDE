//
//  IntentionalClass.m
//  IntentionalIDE
//
//  Created by cfregin on 29.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import "IntentionalClass.h"


@implementation IntentionalClass
@synthesize attributes;
@synthesize methods;
-(id)init{
	self = [super init];
	if(self){
		attributes = [NSMutableDictionary dictionary];
		methods = [NSMutableDictionary dictionary];
	}
	return self;
}
-(void)addMethod:(IntentionalMethod*)method{
	[methods setValue:method forKey:[method methodName]];
}
-(id)callMethod:(NSString*)name{
	IntentionalMethod *method = [methods valueForKey:name];
	[method execute];
	return nil;
}
-(id)bear{
	return nil;
}
@end
