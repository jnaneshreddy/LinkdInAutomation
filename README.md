

````markdown
# LinkedIn Designer Search Automation (Java + Selenium)

This project is a **Java-based Selenium automation script** that logs into LinkedIn, searches for profiles with the keyword **"Designer"**, extracts the names from the search results, and saves them into a **CSV file**. Additionally, it captures a screenshot of the search results page for reference.

---

## 🛠️ Project Requirements

### Prerequisites:
1. **Java JDK 8 or above**
2. **Maven or Gradle (optional but recommended for dependency management)**
3. **Selenium Java Library**
4. **Google Chrome Browser installed**
5. **ChromeDriver (compatible with your installed Chrome version)**

### Selenium Dependency (Maven Example):
```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.11.0</version>
</dependency>
````

---

## 🌐 Environment Variables Setup

To securely handle your LinkedIn credentials, the program uses **environment variables**. Set the following variables in your system before running the program:

| Variable            | Description                    |
| ------------------- | ------------------------------ |
| `LINKEDIN_EMAIL`    | Your LinkedIn account email    |
| `LINKEDIN_PASSWORD` | Your LinkedIn account password |

### How to Set Environment Variables:

#### On Windows (Command Prompt):

```bash
setx LINKEDIN_EMAIL "your-email@example.com"
setx LINKEDIN_PASSWORD "your-secure-password"
```

#### On Mac/Linux (Terminal):

```bash
export LINKEDIN_EMAIL="your-email@example.com"
export LINKEDIN_PASSWORD="your-secure-password"
```

---

## 🚀 How to Run the Project

1. **Download ChromeDriver:**

   * Download the correct version of ChromeDriver from: [https://chromedriver.chromium.org/downloads](https://chromedriver.chromium.org/downloads)
   * Place it in a directory like `drivers/chromedriver.exe`
   * Ensure the path is correctly mentioned in the Java code.

2. **Compile and Run the Program:**

   ```bash
   javac LinkedInDesignerSearch.java
   java LinkedInDesignerSearch
   ```

3. **Expected Output:**

   * A file named `designer_search_results.csv` containing the names of people found in the search.
   * A screenshot saved as `designer_search_screenshot.png` capturing the search result page.
   * Console log displaying the names retrieved during execution.

---

## 📂 Code Workflow Breakdown

### 1. **Login to LinkedIn:**

* Navigates to LinkedIn’s login page.
* Fills in the username and password from environment variables.
* Submits the form and waits until successfully logged in.

### 2. **Search for Keyword 'Designer':**

* Navigates to LinkedIn’s search URL filtered for people with the keyword "Designer".
* Waits until all result elements are loaded on the page.
* Extracts names using a CSS selector targeting result title elements.

### 3. **Save Results to CSV:**

* Creates a CSV file named `designer_search_results.csv`.
* Writes a header row: `Name`
* Iterates through the collected names and appends them to the file.

### 4. **Take Screenshot of Results Page:**

* Captures the current visible viewport of the search results page.
* Saves the screenshot as `designer_search_screenshot.png`.

### 5. **Graceful Exit:**

* Ensures that the browser session is closed properly after execution, even if an error occurs.

---

## ⚠️ Important Notes & Limitations

* LinkedIn strictly prohibits scraping or automation without explicit permission. Use this script responsibly.
* The current script only captures names from the **first page** of search results.
* Handling pagination, scrolling, or deeper data extraction requires additional logic and API-compliant methods.
* Ensure ChromeDriver's version matches your Google Chrome browser version to avoid compatibility issues.

---

## 🔒 Disclaimer

This project is strictly for **educational and personal learning purposes**. Automating interactions on LinkedIn without proper authorization or API usage can lead to account restrictions or permanent bans. Always respect the Terms of Service of any platform you interact with programmatically.

---

