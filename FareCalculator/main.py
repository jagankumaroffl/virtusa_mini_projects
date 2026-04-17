def calculate_fare(km, vehicle_type, hour):
    rates = {'Economy': 10, 'Premium': 18, 'SUV': 25}
    
    if vehicle_type not in rates:
        return "Service Not Available"
    
    rate_per_km = rates[vehicle_type]
    
    surge_multiplier = 1.0
    if hour >= 17 and hour <= 20:
        surge_multiplier *= 1.5
    
    total_fare = km * rate_per_km * surge_multiplier
    
    return total_fare

# check for valid hour
def get_hour_of_day():
    while True:
        try:
            hour = int(input("Enter the hour of day (0-23): "))
            if 0 <= hour < 24:
                break
            else:
                print("Hour must be between 0 and 23.")
        except ValueError:
            print("Invalid input. Please enter an integer value.")
    return hour


# Main program

try:
    distance = float(input("Enter the distance in kilometers: "))
    vehicle_type = input("Enter the type of vehicle (Economy, Premium, SUV): ")
    hour_of_day = get_hour_of_day()
        
    fare_estimate = calculate_fare(distance, vehicle_type, hour_of_day)
    print("\n----- Your Ride Estimate Receipt -----")
    print(f"Distance: {distance:.2f} km")
    print(f"Vehicle Type: {vehicle_type}")
    print(f"Hour of Day: {hour_of_day}")
    print(f"Total Fare: ${fare_estimate:.2f}")
        
except ValueError:
    print("Invalid input. Please enter valid numerical and string values.")
