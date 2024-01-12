Feature: REST API Create, Read and Update ( using PATCH ) Operations

   #PATCH method is used for partial or full object updates.

   @API_Patch_01 @API @Sanity @Regression
  Scenario: To update and validate partial object details.
    Given setup the payload
    And Hit POST Url to create device
    Then Hit PATCH URL to update device details

  @API_Patch_02 @API @Sanity @Regression
  Scenario: To update and validate partial object details.
    Given setup the payload using data table
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1099.99 |M2 chip   | 512 GB         |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device Name
      |Name                |
      |MacBook Air plus    |

  @API_Patch_03 @API @Sanity @Regression
  Scenario: To update and validate partial object details.
    Given setup the payload using data table
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1099.99 |M2 chip   | 512 GB         |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device year
      |Year    |
      |2023    |

  @API_Patch_04 @API @Sanity @Regression
  Scenario: To update and validate partial object details.
    Given setup the payload using data table
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1099.99 |M2 chip   | 512 GB         |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device price
      |Price    |
      |1299.99  |

  @API_Patch_05 @API @Sanity @Regression
  Scenario: To update and validate partial object details.
    Given setup the payload using data table
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1099.99 |M2 chip   | 512 GB         |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device cpu model
      |CPU Model    |
      |M3 chip      |

  @API_Patch_06 @API @Sanity @Regression
  Scenario: validate patch method to update partial device details.
    Given setup the payload using data table
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1099.99 |M2 chip   | 512 GB         |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device hard disk size
      |Hard Disk Size    |
      |1 TB              |

  @API_Patch_07 @API @Sanity @Regression
  Scenario: validate patch method to update partial device details.
    Given setup the payload using data table
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1099.99 |M2 chip   | 512 GB         |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device details from table
      |Name              |Year      | Price   |CPU Model     |
      |MacBook Air Pro   |2023      | 1299.99 |M3 chip plus  |

  @API_Patch_08 @API @Sanity @Regression
  Scenario: validate patch method to update device details.
    Given setup the payload using data table
      |Name           |Year      | Price  |CPU Model | Hard Disk Size |
      |iphone 12 mini |2020      | 599.99 |M3 chip   | 256 GB         |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device details using data table
      |Name                |Year      | Price  |CPU Model      | Hard Disk Size |
      |iphone 12 mini plus |2020      | 599.99 |M3 chip        | 256 GB         |
      |iphone 12 mini plus |2021      | 599.99 |M3 chip        | 256 GB         |
      |iphone 12 mini plus |2022      | 699.99 |M3 chip plus   | 256 GB         |
      |iphone 12 mini plus |2023      | 749.99 |M4 chip plus   | 512 GB         |






