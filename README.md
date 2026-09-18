# House Price Prediction (Basic ML Project)

## About the Project

This is a simple machine learning project where I tried to predict house prices based on a few factors like area, number of bedrooms, and age of the house.

The main goal of this project was to understand how a basic ML model works from start to finish.

---

## Tools Used

* Python
* pandas
* numpy
* scikit-learn

---

## Dataset

I used a small dataset (`house_data.csv`) which contains:

* area (in sq ft)
* bedrooms
* age
* price

---

## What the Code Does

* Reads the dataset
* Splits it into training and testing data
* Trains a Linear Regression model
* Checks how well the model performs using:

  * MSE
  * RMSE
  * R² score
* Takes user input and predicts house price

---

## Results

* R² Score is around **0.97**
* RMSE is around **8000**

Since the dataset is very small, results may change slightly if the data split changes.

---

## How to Run

1. Install required libraries:

```bash
pip install pandas numpy scikit-learn
```

2. Keep the `house_data.csv` file in the same folder

3. Run the Python file:

```bash
python filename.py
```

---

## Example

Input:

```
Area: 1300  
Bedrooms: 3  
Age: 6  
```

Output:

```
Predicted Price: ₹263,787
```

---

## Limitations

* Works only within these ranges:

  * Area: 800–2000
  * Bedrooms: 1–4
  * Age: 1–15
* Very small dataset
* Not accurate for real-world use

---

## What I Learned

* Basic ML workflow
* How to train a model
* How to evaluate it
* How to take input and make predictions

---

## Future Improvements

* Use a bigger dataset
* Add more features
* Try other models

---

## Author

Mohd Minhaz
