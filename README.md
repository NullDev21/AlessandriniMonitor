
# Alessandrini Monitor

Alessandrini Monitor is an open-source Java software designed for managing, maintaining, and installing basic software on over 200 computers, initially tailored for my school environment. It allows technical staff to control and perform rapid maintenance tasks from a central office.

**Note:** This project is in its initial stages and is not yet complete. The repository contains the foundational code, and contributions are welcome to help develop and improve the software.

## Features

- **Centralized Management**: Control multiple computers from a single interface.
- **Rapid Maintenance**: Perform quick maintenance tasks on all connected systems.
- **Software Installation**: Install basic software packages across multiple machines efficiently.
- **User-Friendly Interface**: Easy-to-use interface suitable for technical and non-technical staff.

## Project Structure

The project is divided into several modules, each serving a specific purpose:

- **client**: Contains the client-side code that runs on each managed computer.
- **loader**: Responsible for loading and initializing the software components.
- **panel**: The user interface module for interacting with the system.
- **server**: Contains the server-side code that manages communication between the clients and the central control.

**Note on Naming:** The packages in the project are named "snotix" because it was the first name that came to mind at the beginning of the project.


## Usage

    1. Launch the server application from the central office.
    2. Launch the client application on each computer you want to manage.
    3. Use the panel interface to monitor, maintain, and install software on the connected computers.

## Repository History

This repository includes older code repositories merged into one, which is why there are no individual commit histories for some parts of the project.

## Contributing

Contributions are welcome! Please fork this repository and submit pull requests for any enhancements or bug fixes.
