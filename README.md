# StrateosAssignment

# Moving containers with a robotic arm
Approach to the problem is discussed [here](#approach)



## Problem Statement
At Strateos, our customer samples are stored and transported in containers. A robotic arm moves these containers between holders (a holder holds a single container) for the purpose of executing some scientific experiment.
We'd like to solve the following situation:
- There is only one unoccupied holder, and the remaining holders are holding containers 
- We need to move some number of these containers for the next part of the experiment 
- Robotic arm moves take time, so we want to put each of the plates in the correct holder in as few moves as possible
We want to find the shortest possible sequence of movements to place each container into its corresponding index. When the robotic arm performs these movements, the containers will be sorted alphabetically.


## Input
The input will be a randomized sequence containing consecutive capital letters starting with A in addition to one underscore
- Each index represents a holder 
- A letter represents a container 
- A letter also tells us which holder that container belongs in (container A goes in index 0, B in 1, etc.) 
- The underscore is the empty holder






## Example
*Input sequence*
```javascript
BC_A
```
*Output*
```javascript
1, 2
0, 1
3, 0
```


## Approach
The approach is based on a greedy solution where the swapping decision is based on the position of underscore i.e holder and the number of containers i.e characters that are not in correct position 

*First we traverse through the array check what all characters are not at right position and store them in a map with character and wrong position
Let us consider the below example for the entire approach discussion*

&ensp;BC_A

**Map state after traversal**

&ensp;B 0

&ensp;C 1

&ensp;A 3

*Map contains only characters which are at wrong index*

*Since we are basing are decision to swap based on underscore index*


**There are two cases** :

*Underscore is not at end of the sequence*

&ensp;&ensp;1. Take the character from the map which should be at that index and swap it with the element

&ensp;&ensp;2. C should be at position 2

&ensp;&ensp;3. So swap C at index 1 and underscore at index 2 (1 , 2)
Now sequence is B_CA


*Underscore is at its right position i.e end of the string.
But the other characters are still at wrong position*

&ensp;&ensp;1. Then take the first entry from the map and swap the character


&ensp;&ensp;2. When the character is at the correct index we remove that from the map
Since C is at correct index remove C from map

**Map State after removal**

B 0

A 3

*Therefore we should swap the elements until map size is zero*
