# 📚 This project automates the process of verifying the Certificate of Deposit (CD) calculations using the CIT Bank CD Calculator website. The script performs the following tasks:

- Reads input data (initial deposit, interest rate, term length, compounding frequency, and expected total) from an Excel sheet.
- Interacts with the web-based CD Calculator to calculate the actual total.
- Compares the calculated value with the expected value.
- Writes the result ("Pass" or "Fail") to the Result (F) column of the Excel sheet.
- Highlights the result in green for "Pass" and red for "Fail".

 # File Structure
 
 CITBank_CDCalculator/
│
├── src/
│   ├── DatadrivenProject/
│   │   ├── CITBank_CDCalculator.java        # Main automation script
│   │   ├── XLUtils.java                     # Utility functions for reading/writing Excel
│   └── resources/
│       └── Bankcal.xlsx                     # Excel file with test data
│
├── pom.xml                                   # Maven dependency configuration
└── README.md                                 # Project documentation (this file)

# Automate the Calculation Process:

- The script interacts with the CIT Bank CD Calculator on the website, entering values like initial deposit, interest rate, term length (in months), and compounding frequency (such as monthly or quarterly).
- It then calculates the total return (final amount) based on these values.

# Compare Expected Results with Actual Results:

- The project compares the calculated total (actual result) from the CIT Bank CD Calculator with the expected total (provided in the Excel sheet).
- If the actual total matches the expected total, the result is marked as "Passed." Otherwise, it is marked as "Failed."

# Conclusion
This project can be useful for Quality Assurance (QA) teams to validate the accuracy of financial calculators on websites.
Happy testing! 🚀
