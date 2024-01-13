Feature: REST API Create, Read, Update ( using PATCH ) and Delete Operations

   #PATCH method is used for partial or full object resource updates.

  @API_Patch_01 @API @Sanity @Regression
  Scenario: To update and validate partial object details.
    Given setup device payload
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1499.99 |M1 chip   | 1 TB           |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device modified name
      |Name           |
      |MacBook Pro    |
    And Hit DELETE Url to delete device


  @API_Patch_02 @API @Sanity @Regression
  Scenario: To update and validate partial object details.
    Given setup device payload
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1499.99 |M1 chip   | 1 TB           |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device data modified year
      |Year      | Price   |CPU Model | Hard Disk Size |
      |2023      | 1499.99 |M1 chip   | 1 TB           |
    And Hit DELETE Url to delete device


  @API_Patch_03 @API @Sanity @Regression
  Scenario: To update and validate partial object details.
    Given setup device payload
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1499.99 |M1 chip   | 1 TB           |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device data modified price
     |Year      | Price   |CPU Model | Hard Disk Size |
     |2023      | 1599.99 |M1 chip   | 1 TB           |
    And Hit DELETE Url to delete device


  @API_Patch_04 @API @Sanity @Regression
  Scenario: To update and validate partial object details.
    Given setup device payload
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1499.99 |M1 chip   | 1 TB           |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device data modified cpu model
      |Year      | Price   |CPU Model | Hard Disk Size |
      |2023      | 1599.99 |M2 chip   | 1 TB           |
    And Hit DELETE Url to delete device



  @API_Patch_05 @API @Sanity @Regression
  Scenario: validate patch method to update partial device details.
    Given setup device payload
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1099.99 |M2 chip   | 1 TB           |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device data modified hard disk size
      |Year      | Price   |CPU Model | Hard Disk Size |
      |2023      | 1599.99 |M2 chip   | 2 TB           |
    And Hit DELETE Url to delete device


  @API_Patch_06 @API @Sanity @Regression
  Scenario: validate patch method to update partial device details.
    Given setup device payload
      |Name           |Year      | Price   |CPU Model | Hard Disk Size |
      |MacBook Air    |2022      | 1499.99 |M1 chip   | 1 TB           |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device modified details
      |Name          |Year      | Price   |CPU Model  |Hard Disk Size |
      |MacBook Pro   |2023      | 1599.99 |M3 chip    |2 TB           |
    And Hit DELETE Url to delete device


  @API_Patch_07 @API @Sanity @Regression
  Scenario: validate patch method to update device details.
    Given setup device payload
      |Name           |Year      | Price  |CPU Model       | Hard Disk Size |
      |iphone 15      |2023      | 799.99 |A16 Bionic chip | 512 GB         |
    And Hit POST Url to create device
    Then Hit PATCH URL to update device details using data table
      |Name                |Year      | Price  |CPU Model        | Hard Disk Size |
      |iphone 15 Pro       |2023      | 799.99 |A16 Bionic chip  | 512 GB         |
      |iphone 15 Pro       |2024      | 799.99 |A16 Bionic chip  | 512 GB         |
      |iphone 15 Pro       |2024      | 999.99 |A16 Bionic chip  | 512 GB         |
      |iphone 15 Pro       |2024      | 999.99 |A17 Pro          | 512 GB         |
      |iphone 15 Pro       |2024      | 999.99 |A17 Pro          | 1 TB           |
      |iphone 15 Pro Max   |2024      | 1499.99|A17 Pro Max      | 1 TB           |
    And Hit DELETE Url to delete device







