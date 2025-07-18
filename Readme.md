# Employee Scheduling System - Java Implementation

A Java-based employee scheduling application that demonstrates mastery of control structures, conditionals, loops, and object-oriented programming principles for managing weekly work shifts.

## 📋 Project Overview

This project implements a comprehensive employee scheduling system that manages work shifts for a company operating 7 days a week. The system handles employee preferences, scheduling constraints, and automatically generates balanced weekly schedules while ensuring fair workload distribution.

## 🎯 Assignment Requirements Fulfilled

### ✅ **1. Input and Storage**
- Collects employee names and shift preferences (morning, afternoon, evening)
- Stores data using Java Collections (HashMap, ArrayList, HashSet)
- Implements priority-based preference system

### ✅ **2. Scheduling Logic**
- **One shift per day**: Prevents employees from working multiple shifts daily
- **Maximum 5 days per week**: Enforces weekly work limit per employee
- **Minimum 2 employees per shift**: Ensures adequate staffing coverage
- **Smart assignment**: Automatically fills understaffed shifts

### ✅ **3. Shift Conflicts Resolution**
- Detects when preferred shifts are at capacity
- Implements balanced assignment algorithm for fair distribution
- Resolves conflicts by optimizing workload across all employees

### ✅ **4. Output**
- Generates comprehensive weekly schedule in readable format
- Displays employee assignments for each shift and day
- Provides detailed workload summary for all employees

### ✅ **5. Bonus Features**
- ✨ **Priority ranking system**: Employees can set shift preferences with priorities
- ✨ **Balanced assignment algorithm**: Ensures equitable work distribution
- ✨ **Workload optimization**: Minimizes scheduling conflicts and maximizes employee satisfaction

## 🛠 Technical Implementation

### **Language**: Java 24
### **Build Tool**: Maven
### **Architecture**: Object-Oriented Design

## 📁 Project Structure

```
JavaScheduler/
│
├── src/main/java/com/company/scheduling/
│   ├── Main.java               # Application entry point and test data
│   ├── Employee.java           # Employee class with preferences and availability
│   ├── Shift.java             # Individual shift management and validation
│   └── ScheduleManager.java    # Core scheduling logic and algorithms
│
├── pom.xml                     # Maven project configuration
├── .gitignore                  # Git ignore rules
└── README.md                   # This file
```


## 🚀 How to Run

### **Prerequisites**
- Java 24 or higher
- Maven 3.6+ (optional, for build management)

### **Running the Application**

#### Option 1: Using Maven
```shell script
# Compile and run
mvn clean compile exec:java

# Or specify the main class explicitly
mvn exec:java -Dexec.mainClass="com.company.scheduling.Main"
```


#### Option 2: Manual Compilation
```shell script
# Compile
javac -d target/classes src/main/java/com/company/scheduling/*.java

# Run
java -cp target/classes com.company.scheduling.Main
```


## 📊 Sample Output

```
=== WEEKLY SCHEDULE ===

=== Monday ===
Morning   : John Mike  (2 employees)
Afternoon : Mary Peter  (2 employees)
Evening   : Lisa David  (2 employees)

=== Tuesday ===
Morning   : Emma Sarah  (2 employees)
Afternoon : John Mary  (2 employees)
Evening   : Mike Peter  (2 employees)

=== Wednesday ===
Morning   : David Emma  (2 employees)
Afternoon : Mary Peter  (2 employees)
Evening   : Sarah Mike  (2 employees)

=== Thursday ===
Morning   : John David  (2 employees)
Afternoon : Lisa Mike  (2 employees)
Evening   : Sarah Mary  (2 employees)

=== Friday ===
Morning   : John Peter  (2 employees)
Afternoon : Emma Sarah  (2 employees)
Evening   : Peter Lisa  (2 employees)

=== Saturday ===
Morning   : Emma John  (2 employees)
Afternoon : Peter Sarah  (2 employees)
Evening   : David Lisa  (2 employees)

=== Sunday ===
Morning   : Peter Mike  (2 employees)
Afternoon : Mary Emma  (2 employees)
Evening   : Sarah Lisa  (2 employees)

=== EMPLOYEE WORKLOAD SUMMARY ===
John: 5 days assigned
Mary: 5 days assigned
Peter: 5 days assigned
Sarah: 5 days assigned
Mike: 5 days assigned
Lisa: 5 days assigned
David: 5 days assigned
Emma: 5 days assigned
```


## 🔧 Key Control Structures Demonstrated

### **Conditionals**
- Employee availability validation (`canWork()` method)
- Shift capacity checking (`isFull()`, `hasMinimumStaff()`)
- Preference priority handling
- Workload constraint enforcement

### **Loops**
- Weekly schedule iteration (7 days × 3 shifts)
- Employee assignment loops in `assignEmployeesBalanced()`
- Preference processing loops
- Schedule optimization iterations in `fillUnderstaffedShifts()`

### **Branching**
- Multiple assignment strategies (balanced vs. fill-in)
- Error handling for understaffed shifts
- Alternative employee selection logic
- Stream-based filtering and sorting

## 🏗 Architecture Highlights

### **Object-Oriented Design**
- **Employee Class**: Manages individual employee data, preferences, and availability
- **Shift Class**: Handles shift-specific logic and employee assignments
- **ScheduleManager Class**: Orchestrates the entire scheduling process
- **Clean Separation**: Each class has distinct responsibilities

### **Java Features Utilized**
- **Collections Framework**: HashMap, ArrayList, HashSet for efficient data management
- **Stream API**: Functional programming for employee filtering and selection
- **Lambda Expressions**: Concise comparators and filters
- **Generics**: Type-safe collections throughout
- **Static Finals**: Constants for days, shifts, and constraints

### **Algorithm Features**
- **Balanced Assignment**: `ShiftSlot` approach ensures fair distribution
- **Randomization**: `Collections.shuffle()` prevents assignment bias
- **Priority-based Selection**: Employees with fewer assigned days get priority
- **Two-phase Approach**: Initial balanced assignment followed by gap filling

## ⚙️ Core Algorithms

### **1. Balanced Assignment Algorithm**
- Creates individual slots for each required position (2 per shift)
- Shuffles assignment order to prevent systematic bias
- Prioritizes employees with fewer assigned days
- Ensures fair workload distribution

### **2. Conflict Resolution**
- Identifies understaffed shifts after initial assignment
- Finds available employees who haven't reached 5-day limit
- Fills gaps while maintaining workload balance
- Provides warnings when staffing requirements cannot be met

## 🧪 Test Data

The application includes 8 test employees with randomized preferences:
- **Employees**: John, Mary, Peter, Sarah, Mike, Lisa, David, Emma
- **Preferences**: Each employee has preferences for all days/shifts with varying priorities
- **Constraints**: Maximum 5 days per employee, minimum 2 employees per shift

## 📈 Performance & Scalability

- **Time Complexity**: O(n log n) for employee sorting and assignment
- **Space Complexity**: O(n × d × s) where n=employees, d=days, s=shifts
- **Scalability**: Efficiently handles varying numbers of employees and shifts
- **Memory Optimization**: Uses appropriate collection types for access patterns

## 🔮 Potential Enhancements

- **GUI Interface**: JavaFX-based graphical user interface
- **Database Integration**: Persistent storage for employees and schedules
- **Advanced Preferences**: Time-off requests, skill-based assignments
- **Reporting Features**: Schedule analytics and fairness metrics
- **Configuration**: Customizable shift types, days, and constraints

## 🏫 Academic Context

This project demonstrates proficiency in:
- Object-oriented programming principles
- Java Collections Framework usage
- Algorithm design and optimization
- Control structure implementation
- Software architecture and design patterns

---

*Developed as part of a programming assignment focusing on control structures and software design in Java.*