//
//  IntentionalClass.h
//  IntentionalIDE
//
//  Created by cfregin on 29.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "IntentionalAttribute.h"
#import "IntentionalMethod.h"


@interface IntentionalClass : NSObject {
	NSMutableDictionary *attributes;
	NSMutableDictionary *methods;
}
@property(retain) NSMutableDictionary *attributes;
@property(retain) NSMutableDictionary *methods;

-(id)init;
-(void)addMethod:(IntentionalMethod*)method;
-(id)callMethod:(NSString*)name;
-(id)bear;

@end
