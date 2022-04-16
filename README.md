# ClockDesktop
***
### Description
Desktop app for controlling a UI.Clock art project. A grid of clocks with two identical hands that
can be moved independently of one another. A fixed grid of clocks can be orchestrated to create
synchronized movements or patterns.
***
### Data Format
Clock sequences are stored as JSON files that can be loaded and executed in the UI. 
Clock sequences are formatted in steps where all clocks transition from step to step 
simultaneously. For example step 1 might be that all odd-indexed clocks move 
hand 1 to 180&deg; at 20 deg/s and hand 2 to 0&deg; at 30 deg/s. After all clocks 
have completed their movements, execution moves to the next step.

All angles are based on a unit circle where 0&deg; is at (1,0) increasing CCW to 360.
Consequently, the default direction for a hand to move is CCW. A negative angle is 
treated as a positive angle but in the CW direction. Similarly, a negative speed will 
reverse the current direction.
####Format:
```
{
  "step1" : [
    "row1" : [
      {
        "hand1" : {"angle" : 90,"speed" : 20},
        "hand2" : {"angle" : 45,"speed" : 20}
      },
      {
        "hand1" : {"angle" : -20,"speed" : 30},
        "hand2" : {"angle" : 50,"speed" : 20}
      },
      .
      .
      .
      {
        "hand1" : {"angle" : 110,"speed" : 30},
        "hand2" : {"angle" : 180,"speed" : -60}
      }
    ],
    .
    .
    .
    "rowx" : [
      {
        "hand1" : {"angle" : 90,"speed" : 20},
        "hand2" : {"angle" : 45,"speed" : 20}
      },
      {
        "hand1" : {"angle" : 20,"speed" : 30},
        "hand2" : {"angle" : 50,"speed" : 20}
      },
      .
      .
      .
      {
        "hand1" : {"angle" : 110,"speed" : 30},
        "hand2" : {"angle" : 180,"speed" : 60}
      }
    ]
  ],
  .
  .
  .
  "stepx"   : [
    "row1" : [],
    .
    .
    .
    "rowx" : []
  ]      
}

```
***
### Progress
* Created UI.Clock in JavaFX with abstract controls and macros
* Created Driver application to control multiple clocks at once
***
### Status
* Working on system for generating and managing clock animation sequences
***
### Dependencies
* [JFoenix](http://www.jfoenix.com/) - Material Design UI components
* [GSon](https://github.com/google/gson) - JSON parsing