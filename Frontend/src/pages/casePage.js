import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ExampleClient from "../api/CaseClient";

class CasePage extends BaseClass {

    constructor() {
            super();
            this.bindClassMethods(['onGet', 'onCreate', 'renderCase'], this);
            this.dataStore = new DataStore();
        }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
            document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
            document.getElementById('create-form').addEventListener('submit', this.onCreate);
            this.client = new CaseClient();

            this.dataStore.addChangeListener(this.renderCase)

            //this.fetchConcerts();
        }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderCase() {
            let resultArea = document.getElementById("result-info");

            const cases = this.dataStore.get("case");

            if (cases) {
                resultArea.innerHTML = `
                    <div>ID: ${cases.caseId}</div>
                    <div>Title: ${cases.title}</div>
                    <div>Post Date: ${cases.timeStamp}</div>
                `
            } else {
                resultArea.innerHTML = "No Item";
            }
        }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("case", null);

        let result = await this.client.getCase(id, this.errorHandler);
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

        let title = document.getElementById("create-title-field").value;
        let author = document.getElementById("create-author-field").value;
        let location = document.getElementById("create-location-field").value;
        let timeDate = document.getElementById("create-time-date-field").value;
        let description = document.getElementById("create-description-field").value;
        let potentialSuspects = document.getElementById("create-potential-suspects-field").value;

        const createdCase = await this.client.createCase(title, author, description, location, timeDate, potentialSuspects, this.errorHandler);
        this.dataStore.set("case", createdExample);

        if (createdExample) {
            this.showMessage(`Created ${createdCase.title}!`)
        } else {
            this.errorHandler("Error creating! Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const casePage = new CasePage();
    casePage.mount();
};

window.addEventListener('DOMContentLoaded', main);
