#!/bin/bash
yt-dlp "$1" -o "video/%(title)s.%(ext)s"