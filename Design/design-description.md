Requirements
1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet[1]).

   **We created a class `System` that has an attribute `JobList` (a list of job objects) and the following operations:**

   - **`addJob()` that allows the user to create a new job offer or a current job (`IsCurrentJob`: Boolean = True);** 

   - **`editJob()` that allows to edit any existing job in the list;**

   - **`adjustComparisonSettings(ComparisonSettings)` to adjust comparison settings weights which by default are set uniformly between all factors;**

   - **`compareJobs(Job, Job)` to compare 2 jobs from the list. The list includes both current job and all existing job offers;**

   - **`hasJobOffer()` to check that there are at least two jobs in the list to run the comparison;**

   - **`deleteJon(int)` to delete a specific job from the list.**

2. When choosing to enter current job details, a user will:
   1. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
      1. Title
      2. Company
      3. Location (entered as city and state)
      4. Yearly salary adjusted for cost of living
      5. Yearly bonus adjusted for cost of living
      6. Leave time (in days)
      7. Number of stock option shares offered
      8. Home Buying Program fund (one-time dollar amount up to 15% of Yearly Salary)
      9. Wellness Fund ($0 to $5,000 inclusive annually)
      
      **We created a class `Job` that has 9 attributes listed above as job details namely `Title`, `Company`, `Location`, `AdjustedYearlySalary`, `AdjustedYearlyBonus`, `Leave`, `NumberOfStockOptionSharesOffered`, `HomeBuyingProgramFund`, and `WellnessFund`. In addition to the required job details `Job` class has an attribute `IsCurrentJob` to distinguish between current job and job offer. If `IsCurrentJob` boolean is set to True, the job will appear on the main menu as current job. Otherwise, the job will appear as a job offer. An attribute `Id` may be used to refer to a specific job during the implementation of this app.**
   
   2. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
   
      **This requirement is not shown on the UML design as it is a part of the implementation and will be fulfilled during coding.**

3. When choosing to enter job offers, a user will:
   1. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
   
      **We created the Class `Job` that was described in part 2.1. It fulfills part 3.1 requirements as well. If `IsCurrentJob` boolean is set to False, the job will appear on the main menu as a job offer.**
   
   2. Be able to either save the job offer details or cancel.
   
      **This requirement is not shown on the UML design as it is a part of the implementation and will be fulfilled during coding.**
   
   3. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
   
      **(1) The class `System` has operation `addJob()` to enter another offer and**
     
      **(3) operation `compareJobs(Job, Job)` to compare the offers and operation `hasJobOffer()` to check that there are at least two jobs in the list to run the 
     comparison.**
     
      **(2) This requirement is not shown on the UML design as it is a part of the implementation and will be fulfilled during coding.**

4. When adjusting the comparison settings, the user can assign integer weights to:
   1. Yearly salary
   2. Yearly bonus
   3. Leave time
   4. Number of shares offered
   5. Home Buying Program Fund
   6. Wellness Fund
If no weights are assigned, all factors are considered equal.

   **We created a class `ComparisonSettings` with 6 attributes listed above with default weights uniformly distributed between all factors.**

5. When choosing to compare job offers, a user will:
   1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
   
      **We created a class `JobRankingAlgorithm` that based on current comparison settings ranks jobs from best to worst using operation `rank(List<Jobs>)`. We envision that the main menu will display the top 5 ranked jobs (shown below in part 7) and any time comparison settings are changed the ranking is changed as well.**
   
   2. Select two jobs to compare and trigger the comparison.
   
      **The class `System` has the operation `compareJobs(Job, Job)` to trigger the comparison. The selection of two jobs is not shown in the UML design. It will be implemented later on. In addition, we itroduced the operation `hasJobOffer()` to check that there are at least two jobs in the list to run the comparison.**
   
   3. Be shown a table comparing the two jobs, displaying, for each job:
      1. Title
      2. Company
      3. Location 
      4. Yearly salary adjusted for cost of living
      5. Yearly bonus adjusted for cost of living
      6. Leave time
      7. Number of shares offered
      8. Home Buying Program fund (one-time up to 15% of Yearly Salary)
      9. Wellness Fund fund ($0 to $5,000 inclusive annually)
   
      **The table is not shown on the UML design. It is a part of implementation (specifically, method `compareJobs(Job, Job)`) and will be done during coding.**
   
   4. Be offered to perform another comparison or go back to the main menu.
   
      **It is not shown on the UML design. It is a part of implementation (specifically, method `compareJobs(Job, Job)`) and will be done during coding.**

6. When ranking jobs, a job’s score is computed as the weighted sum of:

   AYS + AYB + (LT * AYS / 260) + (CSO/2) + HBP + WF

   where:
   AYS = yearly salary adjusted for cost of living
   AYB = yearly bonus adjusted for cost of living
   LT = leave time
   CSO = Company shares offered (assuming a 2-year vesting schedule and a price-per-share of $1)
   HBP = Home Buying Program 
   WF= Wellness Fund


   For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus, 2 for wellness fund, and 1 for all other factors, the score would be computed as:

   2/9 * AYS + 2/9 * AYB + 1/9 * (LT * AYS / 260) + 1/9 * (CSO/2) + 1/9 * HBP + 2/9 * WF


   **Is not shown on the UML design. It will be implemented in class `JobRankingAlgorithm` that based on current comparison settings ranks jobs from best to worst using operation `rank(List<Jobs>)`.**


   7. The user interface must be intuitive and responsive.
   
      **Our group member Jessie Luc used the job comparison app "Figma" to create an initial design of our app. The figure is shown below. We will be using this design to ensure intuitive and responsive interface of our app.**
   
      <img src="https://github.gatech.edu/gt-omscs-se-2022fall/6300Fall22Team017/blob/dhnedzko3/GroupProject/Design-Team/group_design_Figma.png" alt="drawing" width="500"/>
   
   8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
   
      **Taken into account.**


________________
[1] To be precise, this functionality will be enabled if there are either (1) at least two job offers, in case there is no current job, or (2) at least one job offer, in case there is a current job.
