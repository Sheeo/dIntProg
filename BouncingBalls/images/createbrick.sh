#!/bin/sh
if [ "x$1" == x ]; then
	echo Usage: "$0 <color>"
	exit 1
fi
convert -size 32x16 xc:"$1" -strokewidth 1 -stroke black -fill none -draw 'rectangle 0,0 31,15' arkanoid_$1.png
