# Snake


## Description

The classic Snake game with 4 game modes and a plethora of options to modify the style.

Game modes are a combination of Normal and Diagonal **movement**, and Normal and Reverse **controls**

## Technologies

Java (JavaFX), NetBeans

## Demo

https://youtu.be/o-_Lqp8HO4c

**Game Modes**
* Normal `Arrow Keys`
* Diagonal `1, 2, 4, 5 (numpad)`
* Reverse _(Controls are reversed)_

**Settings**

* Access by pressing `Shift` on main screen
* Save Settings `ESCAPE`
* Discard Settings `Q`

## Settings you can modify 
*  **color** 
   * Snake (Head & Tail)
     * fill (GUI)
     * stroke (pressing `C` after selecting color)
   * Food
     * fill (GUI)
     * stroke (pressing `SHIFT + C` after selecting color)
   * Game
     * background (pressing `B` after selecting color)

*  **size** 
   * Snake (Head & Tail)
     * piece (GUI)
     * stroke (pressing `S` after selecting size)
   * Food
     * piece (GUI)
     * stroke (pressing `SHIFT + S` after selecting size)

> Settings changed by key presses can be restored to default value by holding `CTRL` followed by appropriate keys
>
> Examples:
> * `CTRL + B` to restore default background color
> * `CTRL + SHIFT + S` to restore default food stroke size
## Modifying Settings with GUI
Use **Add** and **Remove** buttons for appropriate category (Color or Size)

Which element you modify (HEAD, TAIL, FOOD) depends on what radio button is selected at top

GUI representation of values modified will be previewed on the right side:
* COLOR shown in boxes
* SIZE shown as number next to boxes)
### Gradient Options
V1
* **Gradient Checked** and clicking `Add` or `Remove` under appropriate option (COLOR or SIZE)
* Amount added based on slider next to gradient checkmark

V2
* clicking `Add (n)` or `Remove (n)` under appropriate option
* Amount based on n slider at top

> ADDING

if category (HEAD, TAIL, FOOD) has no values (COLOR, SIZE) added, using gradient will just repeat that value

Example (using V2):
* category selected (HEAD)
* n slider set to 5
* size set to 9
* clicking `Add (n)` under size options
   * no values previously added for HEAD SIZE
      *  HEAD SIZE values: [9, 9, 9, 9, 9]
   * value of 0 (zero) previously added for HEAD SIZE
      *  HEAD SIZE values: [0, 1, 3, 5, 7, 9]

> REMOVING

Simply removes the amount set for n

Example (using V2):
* category selected (HEAD)
* n slider set to 3
* HEAD SIZE values [0, 1, 3, 5, 7, 9]
* clicking `Remove (n)` under size options
    *  HEAD SIZE values: [0, 1, 3]

## Game Options (the significance of many values)

`Head Unique` checked
* TAIL values based off of values set for TAIL

`Head Unique` unchecked
* TAIL values based off of values set for HEAD

`Sequence` checked
* pieces will be assigned value in order (according to order values were added)
  * size example:
    * [1, 2, 3, 4]
    * [2, 3, 4, 1]
    * [3, 4, 1, 2]

`Sequence` unchecked
* all pieces will be assigned same value (according to order values were added)
  * size example:
    * [1, 1, 1, 1]
    * [2, 2, 2, 2]
    * [3, 3, 3, 3]

`Frozen` checked
* COLOR or SIZE will update only when FOOD is eaten

`Frozen` unchecked
* COLOR or SIZE will update automatically

### V2 Difference (regarding frozen)
* if `Frozen` checked & `Sequence` unchecked for V2, all pieces will be assigned same value when FOOD is eaten
* for V1, if `Frozen` is checked, it makes no difference if `Sequence` is checked
  * behaves same way as V2's `Frozen` checked & `Sequence` checked

## `CLEAR` & `MIRROR` buttons

Example:
* SIZE radio button selected and HEAD radio button selected
* HEAD SIZE values [2, 4, 5, 10]
* clicking `Mirror` button
  * HEAD SIZE values [2, 4, 5, 10, 5, 4, 2]
* clicking `Clear` button
  * HEAD SIZE values []

## V1 color sliders
* (R, G, B)
* (A)
## V2 color sliders
* (R, G, B)
* (H, S, L)
* (A)
