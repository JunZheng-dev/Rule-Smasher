# Rule Smasher
<h3>Introduction</h3>
This is a fighting game that I made for fun over the 2019 winter break in less than a week, partially following a youtube tutorial.</br>
I implemented my own features such as projectiles, dynamics, health bar etc.</br>
All art is done by me using MS Paint.</br>
Controls for player 1: WASD for movement, F to shoot.</br>
Controls for player 2: Arrow keys for movement, '/' key to shoot.</br>

<h3>Level 1 & 2 Preview</h3>
<kbd>
<img src="https://github.com/JunZheng-dev/Rule-Smasher-Game/blob/master/preview/level1_preview.gif" width="480"  height="270"/>
</kbd>
<kbd>
<img src="https://github.com/JunZheng-dev/Rule-Smasher-Game/blob/master/preview/level2_preview.gif" width="270"  height="270"/>
</kbd>

<h3>How To Make Your Own Map</h3>
You can create your own maps using a text editor by making a grid of numbers.</br>
Save the file to /res/worlds.</br>
Certain blocks are assigned numbers:</br>

0. Sky
1. Dirt
2. Grass
3. Brick
4. Wood
5. Cloud

**The format is as follows:**
```
map_width map_height
player1_spawn_x player1_spawny
player2_spawn_x player2_spawny

map_grid e.g.:
0 0 0 0 0
0 1 1 1 0
0 0 0 0 0
```
