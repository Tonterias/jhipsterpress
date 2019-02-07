import { element, by, ElementFinder } from 'protractor';

export class ActivityComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-activity div table .btn-danger'));
    title = element.all(by.css('jhi-activity div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ActivityUpdatePage {
    pageTitle = element(by.id('jhi-activity-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    activityNameInput = element(by.id('field_activityName'));
    uprofileSelect = element(by.id('field_uprofile'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setActivityNameInput(activityName) {
        await this.activityNameInput.sendKeys(activityName);
    }

    async getActivityNameInput() {
        return this.activityNameInput.getAttribute('value');
    }

    async uprofileSelectLastOption() {
        await this.uprofileSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async uprofileSelectOption(option) {
        await this.uprofileSelect.sendKeys(option);
    }

    getUprofileSelect(): ElementFinder {
        return this.uprofileSelect;
    }

    async getUprofileSelectedOption() {
        return this.uprofileSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class ActivityDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-activity-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-activity'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
