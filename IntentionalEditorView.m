//
//  IntentionalEditorView.m
//  IntentionalIDE
//
//  Created by Carsten Fregin on 30.03.10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "IntentionalEditorView.h"


@implementation IntentionalEditorView
@synthesize methods;
- (id)initWithFrame:(NSRect)frame {
	[self setMethods:[NSMutableArray array]];
//	frame.size.width = 500;
//	frame.size.height = 1000;
//	textView = [[NSTextView alloc] initWithFrame:NSMakeRect(frame.origin.x + 20, frame.origin.y + 20, frame.size.width - 100, frame.size.height - 100)];
	MethodView *view = [[[MethodView alloc] initWithFrame:NSInsetRect(frame, 20, 40)] retain];
	[self addSubview:view];
	[methods addObject:view];
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code here.
    }
    return self;
}

- (void)drawRect:(NSRect)dirtyRect {
	[[NSColor greenColor] set];
	NSBezierPath *path = [NSBezierPath bezierPathWithRoundedRect:[self frame] xRadius:20 yRadius:20];
	[path fill];
    // Drawing code here.
}
- (BOOL)isFlipped{
	return YES;
}

@end
