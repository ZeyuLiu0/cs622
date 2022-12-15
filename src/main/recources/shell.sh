#!/bin/bash
yt-dlp "$1" -o "./video/$2/%(title)s.%(ext)s"