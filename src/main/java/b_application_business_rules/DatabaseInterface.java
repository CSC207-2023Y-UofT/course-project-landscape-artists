this interface is to be implemented by the database stuff in the "Frameworks and Drivers" layer.

this will need access to the entities, as, it needs access to the Repository entity.

this interface can't just pass an entity down to the database itself (for clean architecture reasons or something, idk),
you'll have to passa repository model, which, is basically just another class that contains all the data a Repository object would anyways,
but, something something something clean architecture is why we need to do this or something. idk. this is all really confusing.