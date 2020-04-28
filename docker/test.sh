#!/bin/bash
# adding values in an array
function addarray {
    local sum=0
    local newarray
    newarray=($(echo "$@"))
}