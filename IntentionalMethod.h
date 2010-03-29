//
//  IntentionalMethod.h
//  IntentionalIDE
//
//  Created by cfregin on 29.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import <Foundation/Foundation.h>
enum {
	JAVA_SCRIPT,
	RUBY,
	OBJECTIVE_C
};
typedef NSUInteger IntentionalLanguage;

@interface IntentionalMethod : NSObject {
	IntentionalLanguage language;
	NSString* code;
	NSString* methodName;
	BOOL compiled;
	
}
@property(copy) NSString* code;
@property(copy) NSString* methodName;
@property(assign)IntentionalLanguage language;

-(id)initWithLanguage:(NSUInteger)lang name:(NSString*)name code:(NSString*)theCode;
-(BOOL)compile;
-(id)execute;
@end
