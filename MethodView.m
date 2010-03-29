//
//  MethodView.m
//  IntentionalIDE
//
//  Created by Carsten Fregin on 30.03.10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "MethodView.h"


@implementation MethodView

- (id)initWithFrame:(NSRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        textView = [[NSTextView alloc] initWithFrame:NSInsetRect(frame, 10, 10)];
		[self addSubview:textView];
		dropdown = [[NSPopUpButton alloc] initWithFrame:NSMakeRect(frame.origin.x, frame.origin.y, 180, 40)];
		[dropdown addItemWithTitle:@"Javascript"];
		[dropdown addItemWithTitle:@"Actionscript"];
		[dropdown addItemWithTitle:@"Ruby"];
		[dropdown addItemWithTitle:@"Objective C"];
		[self addSubview:dropdown];
		[textView retain];
		[dropdown retain];
    }
    return self;
}

- (void)drawRect:(NSRect)dirtyRect {
	[[NSColor yellowColor] set];
	
	NSBezierPath *path = [NSBezierPath bezierPathWithRoundedRect:[self frame] xRadius:20 yRadius:20];
	[path fill];
	
}
- (BOOL)isFlipped{
	return YES;
}

@end
