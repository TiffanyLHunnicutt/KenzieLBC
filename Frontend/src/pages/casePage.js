import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ExampleClient from "../api/exampleClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class CasePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onStateChange', 'onSelectCase', 'onOpenCases'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('choose-case-form').addEventListener('submit', this.onGet);
        document.getElementById('add-case-button').addEventListener('click', this.onCreate);
        document.getElementById('submit-openCase-button').addEventListener('submit', this.onCreate);

        this.client = new ExampleClient();

        this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderExample() {
        let resultArea = document.getElementById("choose-case-input");

        const example = this.dataStore.get("case");

        if (example) {
            resultArea.innerHTML = `
                <div>ID: ${example.id}</div>
                <div>Name: ${example.name}</div>
            `
        } else {
            resultArea.innerHTML = "openCase";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("openCases").value;
        this.dataStore.set("case", null);

        let result = await this.client.getExample(id, this.errorHandler);
        this.dataStore.set("case", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("case", null);

        let name = document.getElementById("create-case-field").value;

        const createdExample = await this.client.createExample(name, this.errorHandler);
        this.dataStore.set("case", createdExample);

        if (createdExample) {
            this.showMessage(`Created ${createdExample.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const examplePage = new ExamplePage();
    examplePage.mount();
};

window.addEventListener('DOMContentLoaded', main);
