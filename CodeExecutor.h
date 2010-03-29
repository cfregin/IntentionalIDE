//
//  CodeExecutor.h
//  IntentionalIDE
//
//  Created by cfregin on 29.03.10.
//  Copyright 2010 Carsten Fregin. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "IntentionalClass.h"

@interface CodeExecutor : NSObject {
	IntentionalClass *root;
}
-(id)initWithRoot:(IntentionalClass*)rootClass;
-(BOOL)start;
@end
