function getCityList() {
    fetch("http://localhost:8080/city")
        .then(response => response.json())
        .then(data => {
            var cityElement = document.getElementById("cityList");
            cityElement.innerHTML = "";

            var defaultOption = document.createElement('option');
            defaultOption.disabled = true;
            defaultOption.selected = true;
            defaultOption.textContent = 'Select City';
            cityElement.appendChild(defaultOption);

            data.forEach(element => {
                var optionCity = document.createElement("option");
                optionCity.value = element.cityCode;
                optionCity.textContent = element.cityName;
                cityElement.append(optionCity);
            });
        })
        .catch(error => console.error('Error fetching city options:', error));
}

function getTypeList() {
    fetch("http://localhost:8080/type")
        .then(response => response.json())
        .then(data => {
            var typeElement = document.getElementById("typeList");
            typeElement.innerHTML = "";

            var defaultOption = document.createElement('option');
            defaultOption.disabled = true;
            defaultOption.selected = true;
            defaultOption.textContent = 'Select Type';
            typeElement.appendChild(defaultOption);

            data.forEach(element => {
                var optionType = document.createElement("option");
                optionType.value = element.typeCode;
                optionType.textContent = element.typeName;
                typeElement.appendChild(optionType);
            });
        })
        .catch(error => console.error('Error fetching type options:', error));
}

function getValue() {
    var cityCode = document.getElementById("cityList").value;
    var typeCode = document.getElementById("typeList").value;
    if (cityCode && typeCode) {
        fetch("http://localhost:8080/value?cityCode=" + cityCode + "&typeCode=" + typeCode)
            .then(response => response.json())
            .then(data => {
                var valueElement = document.getElementById("valueList");
                valueElement.innerHTML = "";

                var defaultOption = document.createElement('option');
                defaultOption.disabled = true;
                defaultOption.selected = true;
                defaultOption.textContent = 'Select Value';
                valueElement.appendChild(defaultOption);

                data.forEach(element => {
                    var optionValue = document.createElement("option");
                    optionValue.value = element.Value;
                    optionValue.textContent = element.Value;
                    valueElement.appendChild(optionValue);
                });
            })
            .catch(error => console.error('Error fetching value options:', error));
    }
}

document.getElementById("getReport").addEventListener("click", function () {
    var cityCode = document.getElementById("cityList").value;
    var typeCode = document.getElementById("typeList").value;
    var Value = document.getElementById("valueList").value;
    fetch("http://localhost:8080/utilization?city_code="+cityCode+"&type_code="+typeCode+"&value="+Value)
        .then(response => response.json())
        .then(data => {
            const dataTable = document.getElementById("table");
            dataTable.innerHTML = "";
            const table = document.createElement("table");
            const headerRow = table.insertRow();
            for (const key in data[0]) {
                const headCell = document.createElement("th");
                headCell.textContent = key.charAt(0).toUpperCase() + key.slice(1);
                headerRow.appendChild(headCell);
            }
            console.log(data);
            data.forEach(val => {
                const row = table.insertRow();
                for (const key in val) {
                    const cell = row.insertCell();
                    cell.textContent = val[key];
                }
            });
            dataTable.appendChild(table);
        })
        .catch(error => {
            console.error('Error getting Utilization details:', error);
            alert('Failed to get Utilization details');
        });
});

getCityList();
getTypeList();

document.getElementById("cityList").addEventListener("change", getValue);
document.getElementById("typeList").addEventListener("change", getValue);
