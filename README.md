# Project Manager

A lightweight, open-source task and project manager for solo makers and small teams.

## Overview

**Project Manager** is designed for developers, designers, and small teams who need a simple yet powerful way to organize their work. Unlike heavy enterprise solutions, this tool focuses on what matters most: managing tasks efficiently and collaborating seamlessly.

## Features

- **Kanban Boards** - Visualize your workflow with intuitive drag-and-drop boards
- **Milestones** - Group tasks into meaningful milestones and track progress
- **Prioritized Tasks** - Set priorities to focus on what matters most
- **Comments & Collaboration** - Discuss tasks with your team in context
- **Tags & Labels** - Organize and filter tasks by custom tags
- **Simple Permissions** - Control access for team members without complexity

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+ (or Gradle if applicable)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/lopezenzoa/project-manager.git
cd project-manager
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

## Project Structure

```
project-manager/
├── src/
│   ├── main/          # Application source code
│   └── test/          # Unit and integration tests
├── pom.xml            # Maven configuration
└── README.md          # This file
```

## Development

### Running Tests

Execute the test suite to ensure everything works correctly:

```bash
mvn test
```

### CI/CD

This project includes continuous integration configuration. All pull requests are automatically tested to maintain code quality.

## Contributing

We welcome contributions! Here's how to get started:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes and write tests
4. Commit your changes (`git commit -m 'Add amazing feature'`)
5. Push to your branch (`git push origin feature/amazing-feature`)
6. Open a Pull Request

The codebase is designed to be contribution-friendly with:
- Clear code structure
- Comprehensive test coverage
- Continuous integration checks

## Usage

### Creating a Project

1. Log in to your account
2. Click "New Project"
3. Enter project details and click "Create"

### Managing Tasks

1. Navigate to your project's Kanban board
2. Create tasks by clicking "Add Task"
3. Drag tasks between columns to update their status
4. Assign priorities, tags, and team members as needed

### Collaborating

1. Invite team members to your project
2. Comment on tasks to discuss details
3. Set milestones to track project progress
4. Use tags to organize and filter work

## License

This project is open-source. See the LICENSE file for details.

## Support

If you encounter any issues or have questions:

1. Check existing GitHub Issues
2. Create a new issue with a clear description and steps to reproduce
3. Include relevant logs and environment information

## Roadmap

Future enhancements may include:
- Time tracking and reporting
- Custom workflows
- Integrations with popular tools
- Mobile applications
- Advanced analytics

## Authors

- **Lopez Enzoa** - Initial creator and maintainer

## Acknowledgments

Thanks to all contributors who help make this project better!

---

**Made for makers. Built by makers.**
