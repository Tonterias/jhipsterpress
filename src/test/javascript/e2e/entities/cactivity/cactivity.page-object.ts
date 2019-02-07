import { element, by, ElementFinder } from 'protractor';

export class CactivityComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-cactivity div table .btn-danger'));
    title = element.all(by.css('jhi-cactivity div h2#page-heading span')).first();

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

export class CactivityUpdatePage {
    pageTitle = element(by.id('jhi-cactivity-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    activityNameInput = element(by.id('field_activityName'));
    communitySelect = element(by.id('field_community'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setActivityNameInput(activityName) {
        await this.activityNameInput.sendKeys(activityName);
    }

    async getActivityNameInput() {
        return this.activityNameInput.getAttribute('value');
    }

    async communitySelectLastOption() {
        await this.communitySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async communitySelectOption(option) {
        await this.communitySelect.sendKeys(option);
    }

    getCommunitySelect(): ElementFinder {
        return this.communitySelect;
    }

    async getCommunitySelectedOption() {
        return this.communitySelect.element(by.css('option:checked')).getText();
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

export class CactivityDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-cactivity-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-cactivity'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
