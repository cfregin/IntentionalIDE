//
//  IntentionalEditorView.h
//  IntentionalIDE
//
//  Created by Carsten Fregin on 30.03.10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Cocoa/Cocoa.h>
#import "MethodView.h"

@interface IntentionalEditorView : NSView {
	NSMutableArray *methods;
}
@property(retain) NSMutableArray *methods;
@end
