import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import EvidenceClient from "../api/evidenceClient";

class EvidenceAdmin extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onCreate', 'renderEvidence'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
            document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
            document.getElementById('create-form').addEventListener('submit', this.onCreate);
            this.client = new evidenceClient();

            this.dataStore.addChangeListener(this.renderEvidence)

            //this.fetchConcerts();
        }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderEvidence() {
            let resultArea = document.getElementById("result-info");

            const evidence = this.dataStore.get("evidence");

            if (evidence) {
                resultArea.innerHTML = `
                    <div>ID: ${/cases/{caseId}/evidence/{evidenceId}}</div>
                    <div>Title: ${evidence.title}</div>
                    <div>Post Date: ${evidence.timeStamp}</div>
                `
            } else {
                resultArea.innerHTML = "No evidence";
            }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("evidence", null);

        let result = await this.client.getEvidenceById(id, this.errorHandler);
        this.dataStore.set("evidence", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("evidence", null);


        let evidenceId = document.getElementById("create-evidenceId-field").value;
        let timeStamp = document.getElementById("create-timeStamp-field").value;
        let location = document.getElementById("create-location-field").value;
        let timeDate = document.getElementById("create-time-date-field").value;
        let description = document.getElementById("create-description-field").value;
        let author = document.getElementById("create-author-field").value;

        const createdEvidence = await this.client.createEvidence(evidenceId, timeStamp, location, timeDate,  description, author, this.errorHandler);
        this.dataStore.set("evidence", createdExample);

        if (createdExample) {
            this.showMessage(`Created ${createdEvidence.title}!`)
        } else {
            this.errorHandler("Error creating! Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const evidenceAdmin = new evidenceAdmin();
    evidenceAdmin.mount();
};

window.addEventListener('DOMContentLoaded', main);