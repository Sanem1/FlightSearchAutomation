# Maven clean ve build işlemi
echo "Cleaning and building the project..."
mvn clean install

# TestNG testlerini çalıştır
echo "Running TestNG tests..."
mvn test

# TestNG raporunu aç
echo "Opening the test report..."
xdg-open custom-reports/extent-report.html

echo -e "\e[32mTests ran successfully!\e[0m"
