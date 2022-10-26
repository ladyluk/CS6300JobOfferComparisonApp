# Use Case Model

**Author**: Dziyana Hnedzko

## 1 Use Case Diagram

<img src="https://github.gatech.edu/gt-omscs-se-2022fall/6300Fall22Team017/blob/dhnedzko3/GroupProject/Docs/UseCaseDiagram.png" alt="drawing" width="1000"/>

## 2 Use Case Descriptions

### High-level description
When app is open, the user will be presented with the main menu. The main menu has five extension points that will navigate to different screens.The extansion points are `Create Job`, `Edit Job`, `Delete Job`, `Set Comparison Settings`, and `Compare Jobs`. 
`Create Job` is a generalization of `Create Job Offer` and `Create Current Job`. It allows user to create a new job instance and save it either as a current job (`IsCurrentJob` = True) or a job offer (`IsCurrentJob` = False). **Scenario 1** (see below) sescribes the use of this use case. Once a new job is saved, the user will be able to delete it using `Delete Job` or edit it using `Edit Job` extensions. The use of `Delete Job` is shown in **Scenario 2** below. The use of `Edit Job` is shown in **Scenario 3** below. The extension `Set Comparison Settings` allows user to change default comparison setting (weights that are initially set to 1 == equal contribution). **Scenario 4** demonstrates its use. The app also allows to compare 2 jobs using `Compare Jobs` use case. Once selected, it will check that there are at least two jobs in the main menu to run the comparison. If less than two jobs, it will show an error message to the user. `Compare Jobs` will also run job ranking algorithm and list jobs from best to worst. The user will need to select two jobs to complete the comparison. 

### Pre-conditions
None

### Post-conditions
None

### Scenario 1
**Scenario 1** describes the use of `Create Job` use case. The boxes represent different classes involved in a sequence of events. This scenario has an alternative when saving a new job inctance either as a current job (`IsCurrentJob` = True) or a job offer (`IsCurrentJob` = False).

<img src="https://github.gatech.edu/gt-omscs-se-2022fall/6300Fall22Team017/blob/dhnedzko3/GroupProject/Docs/Scenario1.png" alt="drawing" width="800"/>

### Scenario 2
**Scenario 2** describes the use of `Delete Job` use case. The boxes represent different classes involved in a sequence of events. 

<img src="https://github.gatech.edu/gt-omscs-se-2022fall/6300Fall22Team017/blob/dhnedzko3/GroupProject/Docs/Scenario2.png" alt="drawing" width="800"/>

### Scenario 3
**Scenario 3** describes the use of `Edit Job` use case. The boxes represent different classes involved in a sequence of events. 

<img src="https://github.gatech.edu/gt-omscs-se-2022fall/6300Fall22Team017/blob/dhnedzko3/GroupProject/Docs/Scenario3.png" alt="drawing" width="800"/>

### Scenario 4
**Scenario 4** describes the use of `Set Comparison Settings` use case. The boxes represent different classes involved in a sequence of events. 

<img src="https://github.gatech.edu/gt-omscs-se-2022fall/6300Fall22Team017/blob/dhnedzko3/GroupProject/Docs/Scenario4.png" alt="drawing" width="800"/>

### Scenario 5
**Scenario 5** describes the use of `Compare Jobs` use case. The boxes represent different classes involved in a sequence of events. We envision that our app will always list top 5 ranked jobs in the main menu. `Rank Jobs` checks for current comparison settings to rank jobs. When user chooses to compare jobs, the `System` will launch `compareJobs(Job,Job)` method that first will verify that at least two jobs are present and if that is true, the user will be shown a list of ranked jobs and asked to select two jobs to run the comparison. If less than two jobs are present, the user will be shown an error message. 

<img src="https://github.gatech.edu/gt-omscs-se-2022fall/6300Fall22Team017/blob/dhnedzko3/GroupProject/Docs/Scenario5.png" alt="drawing" width="1300"/>
