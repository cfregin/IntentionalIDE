//
//  ANTLRParser.h
//  IntentionalIDE
//
//  Created by cfregin on 30.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import <Cocoa/Cocoa.h>


@interface ANTLRParser : NSObject {
	NSTask *antlrTask;
}
@property(retain) NSTask *antlrTask;
@end
